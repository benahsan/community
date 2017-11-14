package community

class Family {
    String fileNumber			// index رقم الملف
    String headOfFamily
    Contact contact
    String personResponsible
    Contact contactPersonResponsible
    Country country
    String notes
    static hasMany = [member: Member, address: Address]
    
    static embedded = ['contact', 'contactPersonResponsible', 'country']

    static constraints = {
        fileNumber(blank: false)
        headOfFamily(blank: false)
        country(blank: false)
        contact(blank: false)
        personResponsible(blank: true, nullable: true)
        contactPersonResponsible(blank: true, nullable: true)
        address(blank: true, nullable: true)
        member(blank: true, nullable: true)
        notes(blank: true, nullable: true)
    }

	String toString() { "$headOfFamily" }
}