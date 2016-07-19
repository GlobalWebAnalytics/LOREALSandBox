package com.loreal.sandbox.shared.model;

public enum Brand {
	//@formatter:off
	BIO("Biotherm","BIO"),
	CAC("Cacharel","CAC"),
	CAR("Carita","CAR"),
	CLA("Clarisonic","CLA"),
	DEC("Decleor","DEC"),
	DIE("Diesel","DIE"),
	ESS("Essie","ESS"),
	GAR("Garnier","GAR"),
	GIO("Giorgio Armani Beauty","GIO"),
	GL("Guy Laroche","GL"),
	HR("Helena Rubinstein","HR"),
	KIE("Kiehl's","KIE"),
	KER("Kerastase","KER"),
	LP("L'Oreal Professionnel","LP"),
	OAP("L'Oreal Paris","OAP"),
	CAD("LASCAD - Cadum","CAD"),
	DH("LASCAD - Daniel Hechter","DH"),
	DES("LASCAD - Dessange","DES"),
	EJ("LASCAD - Eau Jeune","EJ"),
	FP("LASCAD - Franck Provost","FP"),
	MEN("LASCAD - Mennen","MEN"),
	MIX("LASCAD - Mixa","MIX"),
	MAR("LASCAD - Narta","MAR"),
	VIL("LASCAD - Vicelle","VIL"),
	LAN("Lancome","LAN"),
	LRP("Laroche Posay","LRP"),
	MM("Maison Martin Margiela","MM"),
	MAK("Makyaj","MAK"),
	MAT("Matrix","MAT"),
	MNY("Maybelline","MNY"),
	MIZ("Mizzani","MIZ"),
	NYX("NYX Cosmetics","NYX"),
	PUR("Pureology","PUR"),
	RL("Ralph Lauren","RL"),
	RED("Redken","RED"),
	RG("Roger & Gallet","RG"),
	SAC("Saç Sirlari","SAC"),
	SAN("Sanoflore","SAN"),
	SHU("Shu Uemura","SHU"),
	SAA("Shu Uemura Art of Hair","SAA"),
	SKIN("SkinCeuticals","SKIN"),
	SC("Softsheen.Carson","SC"),
	TBS("The Body Shop","TBS"),
	UD("Urban Decay","UD"),
	VIC("Vichy","VIC"),
	VR("Victor & Rolf","VR"),
	YSL("YSL Beauty","YSL"),
	YSI("Yue Sai","YSI"),
	
	GWA("Global Web Analytics","GWA");
	//@formatter:on

	private String name = "";
	private String code = "";

	Brand() {

	}

	// Constructeur
	Brand(String name, String code) {
		this.name = name;
		this.code = code;
	}

	@Override
	public String toString() {
		return name;
	}

	public String code() {
		return code;
	}

}
