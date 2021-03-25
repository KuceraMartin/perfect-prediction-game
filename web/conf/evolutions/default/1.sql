-- !Ups
CREATE TABLE "user" (
    "id" UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    "created_at" TIMESTAMP DEFAULT NOW()
);

CREATE TYPE game_type AS ENUM ('nash', 'non-nash');

CREATE TABLE "game" (
    "id" UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    "created_at" TIMESTAMP DEFAULT NOW(),
    "rows" INT NOT NULL,
    "columns" INT NOT NULL,
    "matrix" JSON NOT NULL,
    "seed" INT NOT NULL
);

CREATE TABLE result (
    "id" UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    "created_at" TIMESTAMP DEFAULT NOW(),
    "user_id" UUID NOT NULL,
    "game_id" UUID NOT NULL,
    "game_type" game_type NOT NULL,
    "row_strategy" INT NOT NULL,
    "col_strategy" INT NOT NULL,
    CONSTRAINT "fk_player" FOREIGN KEY ("user_id") REFERENCES "user"("id"),
    CONSTRAINT "fk_game" FOREIGN KEY ("game_id") REFERENCES "game"("id")
);
CREATE INDEX "idx_played_game_user_id" ON result (user_id);
CREATE INDEX "idx_played_game_game_id" ON result ("game_id");


-- !Downs
DROP TABLE result;
DROP TYPE "game_type";
DROP TABLE "game";
DROP TABLE "user";
