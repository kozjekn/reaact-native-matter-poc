# React Native proof of concept for matter commissioning
This project is proof of concept for commissioning matter devices via expo native modules

## Basic Instructions
> **NOTE:** Use `yarn install` after cloning repo
- **Android** `yarn android`

    > For android native development open `./android` dir with android studio (*in Android view*) but edit only `matter-api` project unless you know what you are doing

- **IOS** `yarn ios`
    > For iOS native development open `./ios` dir with xCode

## Resources
- Expo native module: https://docs.expo.dev/modules/native-module-tutorial/
### Android
- Add home SDK to project: https://developers.home.google.com/matter/apis/home
- Instructions for using native android matter lib: https://developers.home.google.com/codelabs/matter-sample-app
- Base page for google home SDK: https://developers.home.google.com/mobile-sdk
### iOS
- Add matter support: https://developer.apple.com/documentation/mattersupport/adding-matter-support-to-your-ecosystem
- Implement commissioning: https://developer.apple.com/documentation/matter/onboarding_a_matter_device