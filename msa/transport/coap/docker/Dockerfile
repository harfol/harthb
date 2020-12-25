FROM thingsboard/openjdk8

COPY start-tb-coap-transport.sh ${pkg.name}.deb /tmp/

RUN chmod a+x /tmp/*.sh \
    && mv /tmp/start-tb-coap-transport.sh /usr/bin

RUN yes | dpkg -i /tmp/${pkg.name}.deb
RUN rm /tmp/${pkg.name}.deb

RUN systemctl --no-reload disable --now ${pkg.name}.service > /dev/null 2>&1 || :

RUN chmod 555 ${pkg.installFolder}/bin/${pkg.name}.jar

USER ${pkg.user}

CMD ["start-tb-coap-transport.sh"]
