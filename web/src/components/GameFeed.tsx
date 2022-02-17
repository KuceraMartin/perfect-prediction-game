import React from 'react'
import Game from './Game'
import { default as GameStruct } from '../api/structures/response/Game'
import GamesService from '../services/GamesService'

enum NewGameStatus {
	Asking = 'asking',
	Loading = 'loading',
	Playing = 'playing',
}

interface GameFeedProps {
	gamesService: GamesService
	onUpdate: () => void
}

interface GameFeedState {
	error: any
	games: GameStruct[]
	newGameStatus: NewGameStatus
}

export default class GameFeed extends React.Component<GameFeedProps, GameFeedState> {

	private readonly gamesService: GamesService

	private readonly rowStrategyNames: string[]
	private readonly colStrategyNames: string[]

	constructor(props: GameFeedProps) {
		super(props);
		this.gamesService = props.gamesService
		this.state = {
			error: null,
			games: [],
			newGameStatus: NewGameStatus.Loading,
		}
		this.handleNewGame = this.handleNewGame.bind(this)

		this.rowStrategyNames = []
		for (let i = 0; i < this.props.gamesService.height; i++) {
			this.rowStrategyNames.push(String.fromCharCode(65 + i))
		}
		this.colStrategyNames = []
		for (let i = 0; i < this.props.gamesService.width; i++) {
			this.colStrategyNames.push(String.fromCharCode(65 + this.props.gamesService.height + i))
		}
	}

	componentDidMount() {
		this.handleNewGame()
	}

	handleNewGame() {
		this.gamesService.newGame().then(
			(game) => {
				this.setState({
					games: this.state.games.concat([game]),
					newGameStatus: NewGameStatus.Playing,
				})
				this.scrollToBottom()
			},
			(error) => this.setState({
				error: error,
			})
		)
	}

	onFinish() {
		this.props.onUpdate()
		this.setState({newGameStatus: NewGameStatus.Asking})
		this.scrollToBottom()
	}

	scrollToBottom() {
		window.scrollTo({
			top: document.body.scrollHeight,
			behavior: "smooth",
		})
	}

	renderGame(game: GameStruct) {
		const play = async (row: number) => {
			const result = await this.gamesService.play(game, row)
			this.onFinish()
			return result
		}
		return <Game
			key={game.id}
			game={game}
			rowStrategyNames={this.rowStrategyNames}
			colStrategyNames={this.colStrategyNames}
			play={play}
		/>
	}

	renderLoading() {
		return <div>Loading...</div>
	}

	renderAgainButton() {
		return <button onClick={this.handleNewGame}>Again</button>
	}

	render() {
		const error = this.state.error

		if (error) {
			return <div>Error: {error.message}</div>
		}

		const games = this.state.games.map(game => this.renderGame(game))
		return (
			<div id="feed">
				{games}
				{this.state.newGameStatus === NewGameStatus.Loading && this.renderLoading()}
				{this.state.newGameStatus === NewGameStatus.Asking && this.renderAgainButton()}
			</div>
		)
	}

}
