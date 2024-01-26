
FROM --platform=$BUILDPLATFORM node:18-alpine AS build

WORKDIR /code
COPY package.json /code/package.json
COPY package-lock.json /code/package-lock.json
RUN npm install

COPY . /code

RUN npm run build

FROM nginx:1.25.0-alpine-slim AS proxy

WORKDIR /etc/nginx/ssl

RUN apk update && \
apk add --no-cache nss curl && \
curl -JLO "https://dl.filippo.io/mkcert/latest?for=linux/amd64" && \
chmod +x mkcert-v*-linux-amd64 && \
cp mkcert-v*-linux-amd64 /usr/local/bin/mkcert

RUN mkcert -install

RUN mkcert -key-file key.pem -cert-file cert.pem localhost

RUN rm /etc/nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY ./nginx.conf /etc/nginx/nginx.conf

WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=build /code/dist .

EXPOSE 80
EXPOSE 443

CMD ["nginx", "-g", "daemon off;"]