# naocustom
Online shop with Nginx reverse proxy and Oauth2

## Run

### Windows
1. Install Docker Desktop: https://docs.docker.com/desktop/install/windows-install/ . Check [wsl 2](https://learn.microsoft.com/en-us/windows/wsl/install) is installed and virtualization is enabled in BIOS. Docker does not work without wsl installed correctly! If you have problems with docker on windows, I recommend to run docker engine in linux virtual machine.

2. Clone this repository and open folder in command prompt

3. Run `docker compose up -d`. First launch may take some minutes

4. Go to https://localhost/ in browser

5. Try to access basket and register with login: firstMerchant and password: 12345678

### Linux

1. You can install [Docker Desktop](https://docs.docker.com/desktop/install/linux-install/). Personally i recommend install just [docker engine](https://docs.docker.com/engine/install/) instead.

2. Clone this repository and open folder in command prompt

3. Run `docker compose up -d`. First launch may take some minutes

4. Go to https://localhost/ in browser

## Overview

![Screenshot of Naocustom website.](/overview/naocustom-overview.png)

This is a demo onlie shop made with React and Spring. Keycloak is an identity provider which provides authorization and authentication. It is very flexible and supports different protocols such as Saml2, Oauth2, Openid Connect and etc.

![Screenshot of Naocustom scheme.](/overview/naocustom-scheme.png)

All static pages are precompiled and hosted by [nginx reverse proxy](https://en.wikipedia.org/wiki/Reverse_proxy). Connection is secured by self-signed tls certificate generated with mkcert. Keycloak provides Oauth2 Authorization Code Flow with PKCE mechanism. You can read more about it in [rfc](https://datatracker.ietf.org/doc/html/rfc7636#section-4). Api is implemented with spring boot container and postgresql database.
