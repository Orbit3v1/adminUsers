
C:\ProgramData\MySQL\MySQL Server 5.7\

max_allowed_packet=32M


<plugin>
                <groupId>org.hibernate.orm.tooling</groupId>
                <artifactId>hibernate-enhance-maven-plugin</artifactId>
                <version>${hibernate.version}</version>
                <executions>
                    <execution>
                        <configuration>
                            <enableDirtyTracking>true</enableDirtyTracking>
                        </configuration>
                        <goals>
                            <goal>enhance</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>