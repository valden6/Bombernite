// 5/10/2018 - Guillaume Bozon
// Classe permettant de savoir le type d'objet sur la case
// Version : 1.1.0

public enum TypeEntity {
    PLAYERSOUTH,
    PLAYERNORTH,
    PLAYEREAST,
    PLAYERWEST,


	MONSTER,
	BOMB,
	BRICK,
	WALL,

	DEFLAGRATION,

	DEFLAGRATIONHORIZONTAL,
	DEFLAGRATIONVERTICAL,

	DEFLAGRATIONNORTHEND,
	DEFLAGRATIONSOUTHEND,
	DEFLAGRATIONWESTEND,
	DEFLAGRATIONEASTEND,

	PLAYERBOMB,

    BONUS_RANGE,
    BONUS_BOMB,
    MALUS_RANGE,
    MALUS_BOMB,

	EMPTY;
}