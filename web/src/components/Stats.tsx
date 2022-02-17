import React from 'react'
import { default as StatsStruct } from '../api/structures/response/Stats'

interface StatsProps {
	stats: StatsStruct
}

export default class Stats extends React.Component<StatsProps> {

	render() {
		return (
			<div id="stats">
				<table>
					<tbody>
						<tr><th>Games:</th><td>{this.props.stats.gamesCount}</td></tr>
						<tr><th>Average score:</th><td>{this.props.stats.averageScore}</td></tr>
					</tbody>
				</table>
			</div>
		)
	}

}
