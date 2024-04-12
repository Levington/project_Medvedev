public static void main(String[] args) {
    File file = new File("music.wav");
    float volume = 0.5f; // Устанавливаем лимит громкости от 0.0 до 1.0
    playMusic(file, volume);
}
public static void playMusic(File file, float volume) {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
        clip.start();

        // Ждем, пока трек не закончится
        while (clip.isOpen() && clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
            Thread.sleep(1000);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
