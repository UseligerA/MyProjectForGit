package salon.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Record {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

 private Client client;


 @Enumerated(EnumType.STRING)
 private final Type type;



    enum Type{
        MEN_HAIRCUT("Мужская стрижка"),
        MEN_BARBER("Услуги барбера"),
        WOMAN_HAIR_DYEING("Окрашивание волос"),
        WOMAN_HAIRCUT("Женская стрижка"),
        WOMAN_NAILS("Ногтевой сервис"),
        WOMAN_BROWS("Услуги бровиста");

        private String name;
        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
