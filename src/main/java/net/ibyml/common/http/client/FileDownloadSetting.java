package net.ibyml.common.http.client;

/**
 * �ļ���������
 * 
 * @author Byml
 *
 */
public interface FileDownloadSetting {
	// �����ļ�����ַ
	String getUrl();

	// �����ļ��Ĵ��Ŀ¼λ��
	String getDirLocation();

	// �����ļ��Ĵ���ļ���
	String getFileName();
}
