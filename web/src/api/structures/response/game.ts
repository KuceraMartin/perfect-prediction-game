import Payoff from '../../Payoff'

export default interface Game {
	id: string
	matrix: Payoff[][]
}
