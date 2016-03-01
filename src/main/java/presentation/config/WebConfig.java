package presentation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.AppCacheManifestTransformer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${resources.projectroot:}")
    protected String projectRoot;

    @Value("${app.version:}")
    protected String appVersion;

    protected String location;

    protected boolean useResourceCache;

    protected String version;

    protected Integer cachePeriod;

    protected String getProjectRootRequired() {
        Assert.state(this.projectRoot != null, "Please set \"resources.projectRoot\" in application.yml");
        return this.projectRoot;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        AppCacheManifestTransformer appCacheTransformer = new AppCacheManifestTransformer();
        VersionResourceResolver versionResolver = new VersionResourceResolver()
                .addFixedVersionStrategy(version, "/**/*.js", "/**/*.css")
                .addContentVersionStrategy("/**");

        registry.addResourceHandler("/**")
                .addResourceLocations(location)
                .setCachePeriod(cachePeriod)
                .resourceChain(useResourceCache)
                .addResolver(versionResolver)
                .addTransformer(appCacheTransformer);
    }




}
