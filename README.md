# Aplicació Escalada – Persistència de Dades (JDBC)
# Hajar Errahmani & Rasen Mediñà
# Dilluns 11 de Maig del 2026

## 1. Descripció del projecte

Aquest projecte consisteix en el desenvolupament d’una aplicació en **Java** per gestionar informació relacionada amb el món de l’escalada: escoles, sectors, vies, llargs i escaladors.

L’aplicació utilitza una **base de dades relacional MySQL** i es connecta mitjançant la **API JDBC**, aplicant una arquitectura basada en **MVC (Model-Vista-Controlador)** i el patró **DAO (Data Access Object)**.

---

## 2. Objectius

- Dissenyar una base de dades relacional normalitzada
- Implementar operacions **CRUD** completes
- Aplicar el patró **MVC**
- Utilitzar **JDBC** per a la persistència de dades
- Implementar el patró **DAO**
- Separar correctament les **capes de l’aplicació**
- Gestionar regles de negoci complexes

---

## 3. Tecnologies utilitzades

- Java
- MySQL
- JDBC (Java Database Connectivity)
- IntelliJ IDEA
- MySQL Workbench
- GitHub

---

## 4. Arquitectura del sistema

El sistema segueix el patró **MVC** amb tres capes diferenciades:

### 4.1. View (Vista)
Gestiona la interacció amb l’usuari:
- Sortida per consola
- Format HTML opcional
- Escriptura a fitxer

**Classes:**
- `Vista`
- `Menus`

---

### 4.2. Controller (Lògica de negoci)

Gestiona:
- Validacions
- Regles del sistema
- Comunicació entre vista i DAO

**Classes:**
- `EscolaController`
- `SectorController`
- `ViaController`
- `EscaladorController`
- `AssolimentController`
- `LlargController`
- `MenuController`

---

### 4.3. DAO (Persistència de dades)

S’encarrega de:
- Connexió amb la base de dades
- Execució de consultes SQL
- Conversió de ResultSet a objectes Java

**Elements JDBC utilitzats:**
- `Connection`
- `PreparedStatement`
- `ResultSet`

---

## 5. JDBC

JDBC és una API que permet connectar Java amb la bases de dades.

### Funcionament:
1. Obtenir connexió (`DBConnection`)
2. Preparar consulta (`PreparedStatement`)
3. Executar consulta
4. Processar resultats (`ResultSet`)
5. Mapejar resultats a objectes Java

---

## 6. Patró DAO

El patró DAO separa la lògica de negoci de l’accés a dades.

### Interfície genèrica:

public interface DAO<T> {
    void create(T t) throws Exception;
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
    void update(T t) throws Exception;
    void delete(int id) throws Exception;
}

### Operacions:
- `Create`
- `getById`
- `getAll`
- `update`
- `delete`

### Implementacions:
- `EscaladorDAOMySQL`
- `ViaDAOMySQL`
- `SectorDAOMySQL`
- `EscolaDAOMySQL`
- `LlargDAOMySQL`
- `AssolimentDAOMySQL`

També s’ha preparat el sistema per a:
- `PostgreSQL` (no implementat, només estructural)

## 7. Model de dades

### Entitats principals:
- `Escola`
- `Sector`
- `Via`
- `Llarg`
- `Escalador`
- `Assoliment`

### Relacions:
- `Una escola → molts sectors`
- `Un sector → moltes vies`
- `Una via → molts llargs`
- `Un escalador → moltes vies (crear)`
- `Relació N:M entre escaladors i vies (Assoliments)`

## 8. Decisions de disseny

### 8.1 Tipus de via
No s’ha utilitzat herència (no hi ha subclasses).
Motiu:

- `Totes comparteixen atributs`
- `Simplificació del model`
- `Millor rendiment`

Control de restriccions → al Controller



```java