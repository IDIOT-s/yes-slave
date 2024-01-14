package org.idiot.yesslave.introduce.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "자기소개 생성을 위한 요청 DTO")
public class IntroduceSaveRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 32, message = "이름은 32자 미만으로 작성해주세요.")
    @Schema(description = "이름", example = "김갑돌")
    private String name;

    //TODO: 이메일 유효성도 체크 필요
    @NotBlank(message = "이메일을 입력해주세요.")
    @Size(max = 64, message = "이메일은 64자 미만으로 작성해주세요.")
    @Schema(description = "이메일", example = "aa@bb.co.kr")
    private String email;

    @Size(max = 512, message = "이미지 URL은 512자 미만으로 작성해주세요.")
    @Schema(description = "프로필 이미지 URL", example = "aa@bb.co.kr")
    private String imageUrl;

    @Size(max = 512, message = "자기소개는 512자 미만으로 작성해주세요.")
    @Schema(description = "자기소개", example = "안녕하세요. 김갑돌입니다.")
    private String bio;

    @Size(max = 512, message = "Github URL은 512자 미만으로 작성해주세요.")
    @Schema(description = "Github URL", example = "https://github.com")
    private String githubUrl;

    @Size(max = 64, message = "회사명은 64자 미만으로 작성해주세요.")
    @Schema(description = "회사명", example = "(주)치킨이맛있어요")
    private String company;

    @Size(max = 256, message = "회사 URL은 64자 미만으로 작성해주세요.")
    @Schema(description = "회사 URL", example = "https://bbq.co.kr/")
    private String companyUrl;

    @Size(max = 256, message = "블로그 URL은 64자 미만으로 작성해주세요.")
    @Schema(description = "블로그 URL", example = "https://section.blog.naver.com/BlogHome.naver")
    private String siteUrl;

    @Size(max = 512, message = "SNS URL 1은 64자 미만으로 작성해주세요.")
    @Schema(description = "첫번째 SNS URL", example = "https://twitter.com/")
    private String snsUrl1;

    @Size(max = 512, message = "SNS URL 2은 64자 미만으로 작성해주세요.")
    @Schema(description = "두번째 SNS URL", example = "http://linkedin.com/")
    private String snsUrl2;
}
