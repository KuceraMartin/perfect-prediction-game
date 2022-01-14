package web.model

import com.github.tminglei.slickpg._


trait SlickPgProfile extends ExPostgresProfile with PgPlayJsonSupport {

  override val pgjson = "json"

  override val api = new API with JsonImplicits

}


object SlickPgProfile extends SlickPgProfile
