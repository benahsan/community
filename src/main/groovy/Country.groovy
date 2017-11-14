class Country {
	String region
	String country
	String origin

	static constraints = {
		region(blank: true, nullable: true, inList: [
			"العرب",
			"شرق آسيا",
			"الغرب"
		])
		country(blank: true, nullable: true, inList: [
			"اليمن",
			"إندونيسيا",
			"ماليزيا",
			"بريطانيا",
			"أستراليا",
			"جنوب أفريقيا",
			"أمريكا",
			"مصر",
			"سوريا",
			"كندا",
			"السويد",
			"إثيوبيا",
			"ألمانيا",
			"الأردن",
			"السودان",
			"الصومال",
			"المكسيك",
			"نميبيا",
			"بلجيكا",
			"تايلندا",
			"جزر القمر",
			"سنغافورا",
			"كرغستان",
			"باكستان",
			"الهند",
			"بروناي",
			"فيليبين"
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// "",
			// ""
		])
		origin(blank: true, nullable: true, inList: [
			"اليمن",
			"إندونيسيا",
			"ماليزيا",
			"بريطانيا",
			"أستراليا",
			"جنوب أفريقيا",
			"أمريكا",
			"مصر",
			"سوريا",
			"كندا",
			"السويد",
			"إثيوبيا",
			"ألمانيا",
			"الأردن",
			"السودان",
			"الصومال",
			"المكسيك",
			"نميبيا",
			"بلجيكا",
			"تايلندا",
			"جزر القمر",
			"سنغافورا",
			"كرغستان",
			"باكستان",
			"الهند",
			"بروناي",
			"فيليبين"
		])
	}

    String toString() { "$origin / $country ($region)" }
}