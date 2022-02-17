import React from 'react'
import Cell, {HighlightType} from './cell'
import Payoff from '../api/Payoff'

interface RowProps {
	payoffs: Payoff[]
	rowStrategyName: string
	isSelectedRowStrategy: boolean
	selectedColStrategy: number | null
	onClick: () => void
}

export default class Row extends React.Component<RowProps> {

	render() {
		let cells = [<th className={`cell highlight-${this.getHighlightType(-1)}`}>{this.props.rowStrategyName}</th>]
		for (let j = 0; j < this.props.payoffs.length; j++) {
			cells.push(this.renderCell(j, this.getHighlightType(j)))
		}
		return <tr className="row-strategy" onClick={this.props.onClick}>
			{cells}
		</tr>
	}

	private getHighlightType(col: number): HighlightType {
		const isRowStrategy = this.props.isSelectedRowStrategy
		const colStrategy = this.props.selectedColStrategy

		if (isRowStrategy && colStrategy === col) {
			return HighlightType.Profile
		}

		if (isRowStrategy || colStrategy === col) {
			return HighlightType.Strategy
		}

		return HighlightType.Default
	}

	private renderCell(col: number, highlight: HighlightType) {
		return (
			<Cell
				key={col}
				payoff={this.props.payoffs[col]}
				highlight={highlight}
			/>
		)
	}

}
