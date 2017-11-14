package community

class Member {
	String name
	String nameInPassport
	String generalNumber		// index رقم العام
	String gender
	Date birth
	int age
    Country country
	String job
	String relationship		// with head of head صلة برب الأسرة
	String notes
    Studies studies
    Boolean travelling
	String deposit
	byte[] featuredImageBytes 
    String featuredImageContentType 

	static belongsTo = [headOfFamily: Family]
    static hasMany = [travel: Travel]
	static embedded = ['country', 'studies']

    static constraints = {
		name(blank: false)
		relationship(blank: true, nullable: true, inList: [
			"رب الأسرة",
			"زوجة",
			"ابن",
			"بنت",
			"أب",
			"أم",
			"أخ",
			"أخت"
		])
		headOfFamily(blank: true, nullable: true)     
		nameInPassport(blank: true, nullable: true)
		generalNumber(blank: true, nullable: true)
		gender(blank: true, nullable: true, inList: ["ذكر", "أنثى"])
		birth(blank: true, nullable: true)
		age(blank: true, nullable: true)
        job(blank: true, nullable: true)
		country(blank: true, nullable: true)
        studies(blank: true, nullable: true)
		notes(blank: true, nullable: true)
        travelling(blank: true, nullable: true)
		travel(blank: true, nullable: true)
		deposit(blank: true, nullable: true)
		featuredImageBytes nullable: true
        featuredImageContentType nullable: true

    }

    static mapping = {
        featuredImageBytes(column: 'featured_image_bytes', sqlType: 'longblob')
    }

	String toString() { "$name" }
}

class Studies {
	Boolean studying
	String studies
    static constraints = {
        studying(blank: true, nullable: true)
		studies(blank: true, nullable: true)
    }

    String toString() { if (studies) "$studies" else "لا يدرس"}
}