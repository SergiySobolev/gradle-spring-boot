var gulp = require('gulp'),
    gutil = require('gulp-util'),
    gclean = require('gulp-clean'),
    gFlatten = require('gulp-flatten'),
    mainBowerFiles = require('main-bower-files')
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

gulp.task('buildDist', ['html','distVendors'], function(){
  //  return gulp.log('Build dist');
});

gulp.task('build', ['clean', 'bower-files', 'buildDist'], function () {
});

gulp.task('watch', function () {
    gulp.watch(paths.src+'index.html', ['buildDist']);
});