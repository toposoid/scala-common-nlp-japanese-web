FROM toposoid/toposoid-core:0.1-SNAPSHOT

WORKDIR /app
ARG TARGET_BRANCH
ENV DEPLOYMENT=local
ENV _JAVA_OPTIONS="-Xms512m -Xmx4g"

RUN git clone https://github.com/toposoid/scala-common-nlp-japanese.git \
&& cd scala-common-nlp-japanese \
&& git checkout ${TARGET_BRANCH} \
&& sbt publishLocal \
&& cd .. \
&& git clone https://github.com/toposoid/scala-common-nlp-japanese-web.git \
&& cd scala-common-nlp-japanese-web \
&& git fetch origin ${TARGET_BRANCH} \
&& git checkout ${TARGET_BRANCH} \
&& sbt playUpdateSecret 1> /dev/null \
&& sbt dist \
&& cd /app/scala-common-nlp-japanese-web/target/universal \
&& unzip -o scala-common-nlp-japanese-web-0.1-SNAPSHOT.zip


COPY ./docker-entrypoint.sh /app/
ENTRYPOINT ["/app/docker-entrypoint.sh"]

