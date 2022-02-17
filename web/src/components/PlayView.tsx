import React from 'react'
import { default as StatsStruct } from '../api/structures/response/Stats'
import GameFeed from './GameFeed'
import Stats from './Stats'
import GamesService from '../services/GamesService'

interface AppProps {
	gameService: GamesService
}

interface AppState {
	error: any
	stats: StatsStruct | null
}

export default class PlayView extends React.Component<AppProps, AppState> {

	private readonly gameService: GamesService

	constructor(props: AppProps) {
		super(props);
		this.gameService = props.gameService
		this.state = {
			error: null,
			stats: null,
		}
		this.handleUpdate = this.handleUpdate.bind(this)
	}

	handleUpdate() {
		this.gameService.stats()
			.then(
				(stats) => this.setState({stats: stats}),
				(error) => this.setState({error: error}),
			)
	}

	render() {
		return (
			<div>
				<GameFeed
					gamesService={this.gameService}
					onUpdate={this.handleUpdate}
				/>
				{this.state.stats && <Stats stats={this.state.stats} />}
				{this.state.error && <div>Error: {this.state.error.message}</div>}
			</div>
		)
	}

}
