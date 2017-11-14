package community

import grails.gorm.services.Service

@Service(Travel)
interface TravelService {

    Travel get(Serializable id)

    List<Travel> list(Map args)

    Long count()

    void delete(Serializable id)

    Travel save(Travel travel)

}