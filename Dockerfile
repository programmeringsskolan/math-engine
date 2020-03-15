FROM clojure:openjdk-11-lein

WORKDIR /usr/src/app

COPY . .

ENTRYPOINT [ "lein" ]

CMD [ "trampoline", "run" ]
