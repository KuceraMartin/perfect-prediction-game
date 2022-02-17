import User from './structures/response/User'
import Game from './structures/response/Game'
import Play from './structures/request/Play'
import Result from './structures/response/Result'
import Stats from './structures/response/Stats'

interface GetParameters {
	headers?: Record<string, string>
}

interface PostParameters extends GetParameters {
	body?: any
}

export default class Client {

	constructor(
		private url: string = 'http://localhost:9000',
		private version: number = 1,
	) {}

	async newUser(): Promise<User> {
		return await this.post('new-user')
	}

	async newGame(rows: number, cols: number): Promise<Game> {
		return await this.post(`new-game?rows=${rows}&cols=${cols}`)
	}

	async play(game: Game, user: User, body: Play): Promise<Result> {
		return await this.post(`play?gameId=${game.id}`, {
			headers: {
				userId: user.id,
			},
			body: body,
		})
	}

	async stats(user: User): Promise<Stats> {
		return await this.get('stats', {
			headers: {
				userId: user.id,
			},
		})
	}

	private async get(path: string, params: GetParameters = {}) {
		return await this.req(path, {
			method: 'GET',
			headers: params.headers,
		})
	}

	private async post(path: string, params: PostParameters = {}) {
		return await this.req(path, {
			method: 'POST',
			headers: params.headers || {},
			body: JSON.stringify(params.body || {}),
		})
	}

	private async req(path: string, init: RequestInit = {}) {
		const response = await fetch(`${this.url}/v${this.version}/${path}`, {
			...init
		})
		return response.json()
	}
}
