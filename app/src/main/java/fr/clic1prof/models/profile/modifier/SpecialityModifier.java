package fr.clic1prof.models.profile.modifier;

public class SpecialityModifier {

    private final int toReplace;
    private final int replaceWith;

    public SpecialityModifier(int toReplace, int replaceWith) {
        this.toReplace = toReplace;
        this.replaceWith = replaceWith;
    }

    public int getToReplace() {
        return this.toReplace;
    }

    public int getReplaceWith() {
        return this.replaceWith;
    }
}
