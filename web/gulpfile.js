var gulp = require('gulp'),
    gutil = require('gulp-util'),
    gclean = require('gulp-clean'),
    gFlatten = require('gulp-flatten'),
    mainBowerFiles = require('main-bower-files'),
    gInject = require('gulp-inject'),
    gDebug = require('gulp-debug')
;

var paths = {
    src: 'app/',
    dist: 'dist/',
    srcVendors: 'app/vendors',
    distVendors: 'dist/vendors'
};

gulp.task("bower-files", function(){
    gulp.src(mainBowerFiles())
        .pipe(gFlatten())
        .pipe(gulp.dest(paths.srcVendors));
});

gulp.task('clean', function(){
    return gulp.src([paths.dist], {read:false})
        .pipe(gclean());
});

gulp.task('html', function () {
    gulp.src(paths.src+'index.html')
        .pipe(gulp.dest(paths.dist));
});

gulp.task('distVendors', function () {
    gulp.src(paths.srcVendors + '/*.js')
        .pipe(gulp.dest(paths.distVendors));
});

gulp.task('index', function () {
    var target = gulp.src(paths.src+'index.html');
    var sources = gulp.src([paths.srcVendors + '/*.js'], {read: false});
    return target
        .pipe(gDebug())
        .pipe(gInject(sources))
        .pipe(gulp.dest(paths.src));
});

gulp.task('buildDist', ['html','distVendors'], function(){
  //  return gulp.log('Build dist');
});

gulp.task('build', ['clean', 'bower-files', 'index', 'buildDist'], function () {
});

gulp.task('watch', function () {
    gulp.watch(paths.src+'index.html', ['buildDist']);
});