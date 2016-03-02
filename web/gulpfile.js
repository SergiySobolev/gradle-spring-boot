var gulp = require('gulp');
var gutil = require('gulp-util');

gulp.task('message', function() {
    return gutil.log('Gulp is running!')
});

gulp.task('html', function() {
    return gutil.log('Html changed')
});

gulp.task('build', ['message'], function(){ });

gulp.task('watch', function() {
    gulp.watch('app/src/index.html', ['html']);
});