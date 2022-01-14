package web

import javax.inject.Inject
import javax.inject.Provider

import play.api.Configuration
import play.api.Environment
import play.api.OptionalSourceMapper
import play.api.http.DefaultHttpErrorHandler
import play.api.routing.Router


class ErrorHandler @Inject() (
  environment: Environment,
  configuration: Configuration,
  sourceMapper: OptionalSourceMapper,
  router: Provider[Router]
) extends DefaultHttpErrorHandler(
  environment = environment,
  sourceMapper = None, // hotfix; see https://github.com/playframework/playframework/issues/10486#issuecomment-763298623
  configuration = configuration,
  router = Some(router.get)
)
