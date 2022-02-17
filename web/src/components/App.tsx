import React from 'react'
import Client from '../api/client'
import {default as GameStruct} from '../api/structures/response/game'
import User from '../api/structures/response/user'
import Game from './game'
import GameType from '../api/structures/game-type'

enum NewGameStatus {
	Asking = 'asking',
	Loading = 'loading',
	Playing = 'playing',
}

interface AppState {
	error: any
	user: User | null
	games: GameStruct[]
	newGameStatus: NewGameStatus
}

export default class App extends React.Component<{}, AppState> {

	private readonly api: Client

	private readonly width: number
	private readonly height: number
	private readonly gameType: GameType

	private readonly rowStrategyNames: string[]
	private readonly colStrategyNames: string[]

	constructor(props: any) {
		super(props);
		this.state = {
			error: null,
			user: null,
			games: [],
			newGameStatus: NewGameStatus.Loading,
		}
		this.api = new Client()
		this.handleNewGame = this.handleNewGame.bind(this)
		this.handleFinish = this.handleFinish.bind(this)

		const url = new URL(window.location.href)
		this.width = Number(url.searchParams.get('w') ?? 3)
		this.height = Number(url.searchParams.get('h') ?? this.width)
		this.gameType = url.searchParams.get('type') === 'nash' ? GameType.Nashian : GameType.NonNashian

		this.rowStrategyNames = []
		for (let i = 0; i < this.height; i++) {
			this.rowStrategyNames.push(String.fromCharCode(65 + i))
		}
		this.colStrategyNames = []
		for (let i = 0; i < this.width; i++) {
			this.colStrategyNames.push(String.fromCharCode(65 + this.height + i))
		}
	}

	componentDidMount() {
		this.api.newUser()
			.then(
				(user) => this.setState({
						user: user,
					}),
				(error) => this.setState({
						error: error
					})
			)
		this.handleNewGame()
	}

	handleNewGame() {
		this.api.newGame(this.width, this.height)
			.then(
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

	handleFinish() {
		this.setState({newGameStatus: NewGameStatus.Asking})
		this.scrollToBottom()
	}

	scrollToBottom() {
		window.scrollTo({
			top: document.body.scrollHeight,
			behavior: "smooth",
		})
	}

	renderGame(game: GameStruct, user: User) {
		return <Game
					key={game.id}
					api={this.api}
					game={game}
					user={user}
					handleFinish={this.handleFinish}
					gameType={this.gameType}
					rowStrategyNames={this.rowStrategyNames}
					colStrategyNames={this.colStrategyNames}
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
		const user = this.state.user

		if (error) {
			return <div>Error: {error.message}</div>
		}

		if (!user) {
			return this.renderLoading()
		}

		const games = this.state.games.map(game => this.renderGame(game, user))
		return (
			<div id="app">
				{games}
				{this.state.newGameStatus === NewGameStatus.Loading && this.renderLoading()}
				{this.state.newGameStatus === NewGameStatus.Asking && this.renderAgainButton()}
			</div>
		)
	}
}
