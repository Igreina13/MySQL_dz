package data;

public enum MainMenuData {

    ADD,
    EDIT,
    LIST,
    SEARCH,
    DELETE,
    EXIT;

    public static MainMenuData allOptions(String string) {
        return switch (string.toLowerCase().trim()) {
            case "add", "создать", "добавить", "внести", "1" -> ADD;
            case "edit", "редактировать", "изменить", "2" -> EDIT;
            case "list", "список", "показать", "3" -> LIST;
            case "search", "поиск", "найти", "4" -> SEARCH;
            case "delete", "удалить", "убрать", "5" -> DELETE;
            case "exit", "выход", "закрыть", "6" -> EXIT;
            default -> throw new IllegalStateException();
        };
    }
}
