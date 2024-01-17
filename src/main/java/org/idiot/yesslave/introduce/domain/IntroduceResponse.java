package org.idiot.yesslave.introduce.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "자기소개 조회 DTO")
public class IntroduceResponse {

    @Schema(description = "이름", example = "김갑돌")
    private String name;

    @Schema(description = "이메일", example = "aa@bb.co.kr")
    private String email;

    @Schema(description = "프로필 이미지 URL", example = "http://aa.com")
    private String imageUrl;

    @Schema(description = "자기소개", example = "안녕하세요. 김갑돌입니다.")
    private String bio;

    @Schema(description = "Github URL", example = "https://github.com")
    private String githubUrl;

    @Schema(description = "회사명", example = "(주)치킨이맛있어요")
    private String company;

    @Schema(description = "회사 URL", example = "https://bbq.co.kr/")
    private String companyUrl;

    @Schema(description = "블로그 URL", example = "https://section.blog.naver.com/BlogHome.naver")
    private String siteUrl;

    @Schema(description = "첫번째 SNS URL", example = "https://twitter.com/")
    private String snsUrl1;

    @Schema(description = "두번째 SNS URL", example = "http://linkedin.com/")
    private String snsUrl2;

    public static IntroduceResponse of(Introduce introduce) {
        IntroduceResponse response = new IntroduceResponse();

        response.name = introduce.getName();
        response.email = introduce.getEmail();
        response.imageUrl = introduce.getImageUrl();
        response.bio = introduce.getBio();
        response.githubUrl = introduce.getGithubUrl();
        response.company = introduce.getCompany();
        response.companyUrl = introduce.getCompanyUrl();
        response.siteUrl = introduce.getSiteUrl();
        response.snsUrl1 = introduce.getSnsUrl1();
        response.snsUrl2 = introduce.getSnsUrl2();

        return response;
    }
}
