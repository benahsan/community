package community

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@SuppressWarnings('LineLength')
class MemberGormService {
    @Transactional
    Member updateMemberFeaturedImage(Long memberId, Integer version, byte[] bytes, String contentType) {
        Member member = Member.get(memberId)
        if ( !member ) {
          return null
        }
        member.version = version
        member.featuredImageBytes = bytes
        member.featuredImageContentType = contentType
        member.save()
        member
    }
}