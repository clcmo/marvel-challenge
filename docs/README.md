# Marvel Challenge

[![GitHub license](https://img.shields.io/github/license/clcmo/marvel-challenge?style=for-the-badge)](https://github.com/clcmo/marvel-challenge)
[![GitHub stars](https://img.shields.io/github/stars/clcmo/marvel-challenge?style=for-the-badge)](https://github.com/clcmo/marvel-challenge/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/clcmo/marvel-challenge?style=for-the-badge)](https://github.com/clcmo/marvel-challenge/network)
[![GitHub issues](https://img.shields.io/github/issues/clcmo/marvel-challenge?style=for-the-badge)](https://github.com/clcmo/marvel-challenge/issues)
[![GitHub donate](https://img.shields.io/github/sponsors/clcmo?color=pink&style=for-the-badge)](https://github.com/sponsors/clcmo)

## About

The objective of this challenge is to implement an app where you can see the list of Marvel characters.

- [x] The app should show a list and be able to navigate to the details of each character.
- [x] In addition, the character can be favorite both in the list and in the details screen.
- [ ] Favorite characters must be persisted on the device so that they can be accessed offline and shown in a favorites tab.

## API

To develop the app you will need to use the Marvel API "Characters" endpoint.
[More information](https://developer.marvel.com/docs).

## Interface

The app's interface is divided into 3 parts and must be developed according to the points below.

- Home:

- [ ] Button to favorite the item of each character.
- [x] Search item to filter a character by name in the list of characters.
- [x] Have an empty list interface, error or no internet.

- Character details:

- [ ] Favorite button.
- [x] Full size photo
- [x] Description (if any).

- Favorites:

- [ ] List of characters favored by the user.
- [ ] Empty list interface, error or no internet.

- Extras: 

- Include Series, Features, Events and Stories.

## Requires

- [x] Use Kotlin
- [x] Handling for connection failure.
- [ ] Unitary tests.
- [x] Screens working in Landscape and Portrait.
- [x] Integration with some CI/CD tool - Github's own 'Actions' can be used.
- [x] Implement on-screen accessibility.


| Details | List |
| ------- | ---- |
| ![Screenshot Details](https://github.com/clcmo/marvel-challenge/blob/main/docs/images/DetailsCharacter.jpeg?raw=true) | ![Screenshot List](https://github.com/clcmo/marvel-challenge/blob/main/docs/images/ListCharacters.jpeg?raw=true) | 

Licence: [MIT](LICENSE)
