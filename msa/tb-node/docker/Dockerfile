FROM thingsboard/openjdk8

COPY start-tb-node.sh ${pkg.name}.deb /tmp/

RUN chmod a+x /tmp/*.sh \
    && mv /tmp/start-tb-node.sh /usr/bin

RUN yes | dpkg -i /tmp/${pkg.name}.deb
RUN rm /tmp/${pkg.name}.deb

RUN systemctl --no-reload disable --now ${pkg.name}.service > /dev/null 2>&1 || :

RUN chown -R ${pkg.user}:${pkg.user} /tmp

RUN chmod 555 ${pkg.installFolder}/bin/${pkg.name}.jar

USER ${pkg.user}

CMD ["start-tb-node.sh"]
