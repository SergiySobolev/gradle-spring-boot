var gulp = require('gulp'),
    gutil = require('gulp-util'),
    gclean = require('gulp-clean'),
    gFlatten = require('gulp-flatten'),
    mainBowerFiles = require('main-bower-files'),
    gInject = require('gulp-inject'),
    gDebug = require('gulp-debug'),
    gRunSequence = require('run-sequence'),
    angularFileSort = require('gulp-angular-filesort'),
    series = require('stream-series'),
    gPrint = require('gulp-print')
;

var paths = {
    any: '**/*',
    src: 'src/',
    dist: 'dist/',
    distVendors: 'dist/vendors',
    distApp: 'dist/app'
};

var options = {
    errorHandler: function(title) {
        return function(err) {
            gutil.log(gutil.colors.red('[' + title + ']'), err.toString());
            this.emit('end');
        };
    }
};


gulp.task('clean', function(){
    return gulp.src([paths.dist], {read:false})
        .pipe(gclean());
});

gulp.task('buildDist', [], function(){

    gulp.src(paths.src+paths.any)
        .pipe(gulp.dest(paths.dist));

    return gulp.src(mainBowerFiles())
        .pipe(gFlatten())
        .pipe(gulp.dest(paths.distVendors));
});

gulp.task('injectDist', [], function(){
    var injectOptions = {
        relative:true
    };
    var target = gulp.src(paths.dist+'index.html');
    var angularSource = gulp.src(paths.distVendors + "/angular.js");
    var vendorSources = gulp.src([paths.distVendors + "**/*.js", "!dist/vendors/angular.js"], {read: false});
    var appSources = gulp.src(paths.distApp + "/**/*.js");
    vendorSources.pipe(gPrint());
    return target
        .pipe(gDebug())
        .pipe(gInject(series(angularSource, vendorSources, appSources.pipe(angularFileSort())), injectOptions))
        .pipe(gulp.dest(paths.dist));
});

gulp.task('build', function () {
    gRunSequence('injectDist');
});

gulp.task('watch', function () {
    gulp.watch(paths.src+paths.any, ['build']);
});