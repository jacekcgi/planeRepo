override properties using jvm arguments:
example:
-Dspring.config.location=C:/Users/some/path/to/folder -Dspring.profiles.active={profile}

spring boot will override those properties from application.properties that are present in
resources:/application-{profile}.properties
and
C:/Users/some/path/application-{profile}.properties

