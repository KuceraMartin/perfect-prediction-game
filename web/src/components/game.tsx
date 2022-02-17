import React from 'react';
import Client from '../api/client'
import { default as GameStruct } from '../api/structures/response/game'
import Board from '../components/board'
import User from '../api/structures/response/user'
import GameType from '../api/structures/game-type'

interface GameProps {
	api: Client
	game: GameStruct
	user: User
	gameType: GameType
	rowStrategyNames: string[]
	colStrategyNames: string[]
	handleFinish: () => void
}

interface GameState {
	error: any
	rowStrategy: number | null
	colStrategy: number | null
}

export default class Game extends React.Component<GameProps, GameState> {

	constructor(props: GameProps) {
		super(props);
		this.state = {
			error: null,
			rowStrategy: null,
			colStrategy: null,
		}
	}

	handleClick(row: number) {
		this.setState({rowStrategy: row})
		this.props.api.play(this.props.game, this.props.user, {
			gameType: this.props.gameType,
			rowStrategy: row,
		})
			.then(
				(result) => {
					this.setState({colStrategy: result.columnStrategy})
					this.props.handleFinish()
				},
				(error) => this.setState({error: error})
			)
	}

	render() {
		const error = this.state.error
		if (error) {
			return <div>Error: {error.message}</div>
		}

		return (
			<div className="game">
				<Board
					matrix={this.props.game.matrix}
					rowStrategyNames={this.props.rowStrategyNames}
					colStrategyNames={this.props.colStrategyNames}
					rowStrategy={this.state.rowStrategy}
					colStrategy={this.state.colStrategy}
					onClick={row => this.handleClick(row)}
				/>
			</div>
		)
	}
}
