FROM thingsboard/base

COPY start-js-executor.sh ${pkg.name}.deb /tmp/

RUN chmod a+x /tmp/*.sh \
    && mv /tmp/start-js-executor.sh /usr/bin

RUN yes | dpkg -i /tmp/${pkg.name}.deb
RUN rm /tmp/${pkg.name}.deb

RUN systemctl --no-reload disable --now ${pkg.name}.service > /dev/null 2>&1 || :

RUN chmod 555 ${pkg.installFolder}/bin/${pkg.name}

USER ${pkg.user}

CMD ["start-js-executor.sh"]
