export interface CategoryPercentage {
  categoryName: string;
  percentage: number;
}

export interface MessageContent {
  avatarUrl: string;
  title: string;
  content: string;
  time: string;
}

export interface FileType {
  url: string;
  name: string;
  type: string;
  id: number | null;
}
