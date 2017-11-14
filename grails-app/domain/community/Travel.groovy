package community

class Travel {
    Date travel
    String purpose
    Date returning
    static belongsTo = [member: Member]

    static constraints = {
        member(blank: false)
        travel(blank: false)
        purpose(blank: false)
        returning(blank: true, nullable: true)
    }

    String toString() { 
        def dateOfTravel
        if (travel) {
            dateOfTravel = travel.format('yyyy-MM-dd')
        }
        def dateOfReturn = ""
        if (returning) {
            dateOfReturn = "إلى " + returning.format('yyyy-MM-dd')
        }
        "($purpose) $dateOfTravel $dateOfReturn" 
    }
}
