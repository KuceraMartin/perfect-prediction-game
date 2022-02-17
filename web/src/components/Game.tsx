import React from 'react';
import { default as GameStruct } from '../api/structures/response/Game'
import Board from './Board'
import Result from '../api/structures/response/Result'

interface GameProps {
	game: GameStruct
	rowStrategyNames: string[]
	colStrategyNames: string[]
	play: (rowStrategy: number) => Promise<Result>
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
		if (this.state.rowStrategy !== null) {
			return
		}
		this.setState({rowStrategy: row})
		this.props.play(row).then(
			(result) => this.setState({colStrategy: result.columnStrategy}),
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
