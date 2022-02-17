import User from '../api/structures/response/User'
import Game from '../api/structures/response/Game'
import Client from '../api/Client'
import Result from '../api/structures/response/Result'
import Stats from '../api/structures/response/Stats'
import GameType from '../api/structures/GameType'

export default class GamesService {

	constructor(
		private readonly api: Client,
		public readonly width: number,
		public readonly height: number,
		private readonly gameType: GameType,
		private readonly user: User,
	) {}

	newGame(): Promise<Game> {
		return this.api.newGame(this.height, this.width)
	}

	play(game: Game, rowStrategy: number): Promise<Result> {
		return this.api.play(game, this.user, {
			gameType: this.gameType,
			rowStrategy: rowStrategy,
		})
	}

	stats(): Promise<Stats> {
		return this.api.stats(this.user)
	}

}
