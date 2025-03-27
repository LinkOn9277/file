/*********************************************************
 * 외부자원을 내부에 연동 관련 클래스

 * SpringBoot 버전에 따라서 설정 방법이 다르다.

 * SpringBoot는 Resources 자원만 이용이 가능
 * 외부폴더 및 S3 같은 클라우드 자원을 Resources에 등록해서 내부자원처럼 이용

 * 클래스 생성 : new → 동일한 클래스를 여러개 만들어서 사용
 * 클래스 상속 : 기존 클래스에 다른 클래스를 결합해서 하나의 클래스처럼 사용

 * class 클래스 : extends → 클래스에 메소드를 이용
 * interface : implements → 클래스에 메소드를 변경하면서 사용

 *********************************************************/
package com.team.itemfileupload.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}") // 반복적인 값을 properties 저장하고 사용 , 수정은 properties 만하면 모두 적용
    String uploadPath;      // application.properties 저장된 변수의 값을 읽어와서 저장

    // 기존 클래스에 존재하는 메소드의 내용을 변경해서 사용
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations(uploadPath);
    }
    // c:/item/image 폴더를 /image/** 이름으로 내부 자원으로 지정해서 사용

}
