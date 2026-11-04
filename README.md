# BHAJAN SANGAM

## Automatically listed offline songs

Put audio files into:

`app/src/main/assets/songs/`

Supported formats: MP3, M4A, AAC, WAV, OGG and FLAC.

When you run a new GitHub Actions build, Gradle scans that folder and automatically generates `app/src/main/assets/song-list.json`. The app reads that catalogue and displays every included song. You do not need to edit `index.html` when adding songs.

Song names are generated from filenames:
- `mero_bhajan.mp3` appears as `mero bhajan`
- `krishna-aarti.m4a` appears as `krishna aarti`

The audio files are packaged into the APK and can play without internet. Adding more songs increases APK size.

## Build
Push the updated project to GitHub or run the included GitHub Actions workflow.
