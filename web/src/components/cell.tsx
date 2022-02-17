import React from 'react'
import Payoff from '../api/Payoff'

export enum HighlightType {
	Default = 'default',
	Strategy = 'strategy',
	Profile = 'profile',
}

interface CellProps {
	payoff: Payoff
	highlight: HighlightType
}

export default function Cell(props: CellProps) {

	return <td className={`cell highlight-${props.highlight}`}>
			{props.payoff[0]}, {props.payoff[1]}
		</td>

}
