var gulp = require('gulp'),
    gutil = require('gulp-util'),
    gclean = require('gulp-clean'),
    gFlatten = require('gulp-flatten'),
    mainBowerFiles = require('main-bower-files')
;

var paths = {
    src: 'app/',
    dist: 'dist/',
    vendors_dir: 'bower_components/',
    vendors:['angular/angular.js', 'angular-resource/angular-resource.js']
};

gulp.task("bower-files", function(){
    gulp.src(mainBowerFiles())
        .pipe(gFlatten())
        .pipe(gulp.dest("./app/vendors"));
});

gulp.task('clean', function(){
    return gulp.src([paths.dist], {read:false})
        .pipe(gclean());
});

gulp.task('html', function () {
    gulp.src(paths.src+'index.html')
        .pipe(gulp.dest(paths.dist));
});

gulp.task('build', ['clean', 'bower-files'], function () {
});

gulp.task('watch', function () {
    gulp.watch(paths.src+'index.html', ['html']);
});