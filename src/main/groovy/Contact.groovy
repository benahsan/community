class Contact {
    String email
    String whatsappNumber
    String mobileNumber
    String phoneNumber

    static constraints = {
        email(blank: true, nullable: true)
        whatsappNumber(blank: true, nullable: true)
        mobileNumber(blank: true, nullable: true)
        phoneNumber(blank: true, nullable: true)
    }

    String toString() { "$whatsappNumber / $mobileNumber" }
}