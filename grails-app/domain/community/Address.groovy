package community

class Address {
    static belongsTo = [family: Family]
    String flatNumber
    String address
    String landlordName
    String landlordPhone
    String houseDescription
    String howMuch
    Date endOfRent
    String notes

    static constraints = {
        family(blank: true, nullable: true)
        flatNumber(blank: true, nullable: true)
        address(blank: true, nullable: true)
        landlordName(blank: true, nullable: true)
        landlordPhone(blank: true, nullable: true)
        houseDescription(blank: true, nullable: true)  
        howMuch(blank: true, nullable: true)
        endOfRent(blank: true, nullable: true)
        notes(blank: true, nullable: true)
    }

    String toString() { "$flatNumber / $address" }

}