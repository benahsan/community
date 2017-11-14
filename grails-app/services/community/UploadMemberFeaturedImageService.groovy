
package community

class UploadMemberFeaturedImageService {

    def memberGormService

    Member uploadFeatureImage(FeaturedImageCommand cmd) {
        byte[] bytes = cmd.featuredImageFile.bytes
        String contentType = cmd.featuredImageFile.contentType
        memberGormService.updateMemberFeaturedImage(cmd.id, cmd.version, bytes, contentType)
    }
}
