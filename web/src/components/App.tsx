import React from 'react'
import PlayView from './PlayView'
import Client from '../api/Client'
import GameType from '../api/structures/GameType'
import GamesService from '../services/GamesService'

interface AppState {
	error: any
	gamesService: GamesService | null
}

export default class App extends React.Component<{}, AppState> {

	private readonly api: Client
	private readonly gameWidth: number
	private readonly gameHeight: number
	private readonly gameType: GameType

	constructor(props: {}) {
		super(props)
		this.state = {
			error: null,
			gamesService: null,
		}

		const url = new URL(window.location.href)
		this.api = new Client()
		this.gameWidth = Number(url.searchParams.get('w') ?? 3)
		this.gameHeight = Number(url.searchParams.get('h') ?? this.gameWidth)
		this.gameType = url.searchParams.get('type') === 'nash' ? GameType.Nashian : GameType.NonNashian
	}

	componentDidMount() {
		this.api.newUser()
			.then(
				(user) => this.setState({
					gamesService: new GamesService(
							this.api,
							this.gameWidth,
							this.gameHeight,
							this.gameType,
							user,
						),
				}),
				(error) => this.setState({error: error})
			)
	}

	render() {
		let content = <div>Loading...</div>
		if (this.state.gamesService) {
			content = <PlayView gameService={this.state.gamesService} />
		}
		return <div id="app">{content}</div>
	}

}
