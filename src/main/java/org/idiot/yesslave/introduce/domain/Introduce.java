package org.idiot.yesslave.introduce.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.idiot.yesslave.global.jpa.AuditInformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "INTRODUCE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Introduce extends AuditInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTRODUCE_ID", nullable = false)
    private Long id;

    @NotNull
    @Comment("이름")
    @Column(name = "NAME", nullable = false, length = 32)
    private String name;

    @NotNull
    @Comment("이메일")
    @Column(name = "EMAIL", nullable = false, length = 64)
    private String email;

    @Comment("이미지 URL")
    @Column(name = "IMAGE_URL", length = 512)
    private String imageUrl;

    @Comment("자기소개")
    @Column(name = "BIO", length = 512)
    private String bio;

    @Comment("깃헙 URL")
    @Column(name = "GITHUB_URL", length = 512)
    private String githubUrl;

    @Comment("회사명")
    @Column(name = "COMPANY", length = 64)
    private String company;

    @Comment("회사 URL")
    @Column(name = "COMPANY_URL", length = 256)
    private String companyUrl;

    @Comment("블로그 URL")
    @Column(name = "SITE_URL", length = 256)
    private String siteUrl;

    @Comment("SNS URL 1")
    @Column(name = "SNS_URL_1", length = 512)
    private String snsUrl1;

    @Comment("SNS URL 2")
    @Column(name = "SNS_URL_2", length = 512)
    private String snsUrl2;

    public static Introduce registerOf(IntroduceSaveRequest request) {
        Introduce introduce = new Introduce();

        introduce.name = request.getName();
        introduce.email = request.getEmail();
        introduce.imageUrl = request.getImageUrl();
        introduce.bio = request.getBio();
        introduce.githubUrl = request.getGithubUrl();
        introduce.company = request.getCompany();
        introduce.siteUrl = request.getSiteUrl();
        introduce.snsUrl1 = request.getSnsUrl1();
        introduce.snsUrl2 = request.getSnsUrl2();

        return introduce;
    }
}
