import React from 'react'
import Payoff from '../api/Payoff'
import Row from './Row'
import {HighlightType} from './Cell'

interface BoardProps {
	matrix: Payoff[][]
	rowStrategyNames: string[]
	colStrategyNames: string[]
	rowStrategy: number | null
	colStrategy: number | null
	onClick: (row: number) => void
}

export default class Board extends React.Component<BoardProps> {

	render() {
		return (
			<table className={this.props.colStrategy === null ? 'active' : 'finished'}>
				{this.renderHeader()}
				{this.renderBody()}
			</table>
		)
	}

	private renderHeader() {
		const cells = [<th key="-1"/>]
		for (let j = 0; j < this.props.colStrategyNames.length; j++) {
			const highlight = (this.props.colStrategy === j) ? HighlightType.Strategy : HighlightType.Default
			cells.push(
				<th
					key={j}
					className={`cell highlight-${highlight}`}
				>{this.props.colStrategyNames[j]}</th>
			)
		}
		return (
			<thead>
				<tr>{cells}</tr>
			</thead>
		)
	}

	private renderBody() {
		const rows = []
		for (let i = 0; i < this.props.matrix.length; i++) {
			rows.push(this.renderRow(i))
		}
		return <tbody>{rows}</tbody>
	}

	private renderRow(i: number) {
		const rowStrategy = this.props.rowStrategy
		const colStrategy = this.props.colStrategy
		return <Row
				key={i}
				payoffs={this.props.matrix[i]}
				rowStrategyName={this.props.rowStrategyNames[i]}
				isSelectedRowStrategy={i === rowStrategy}
				selectedColStrategy={colStrategy}
				onClick={() => this.props.onClick(i)}
			/>
	}
}
