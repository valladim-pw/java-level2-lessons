package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTree2;

//public class TreePrinter {
//		private List<String> printList;
//		private LinkedList<Integer> levelList;
//		private int maxValueLength;
//		private int maxStringLength;
//
//		public void printTree(Node<K, V> root) {
//			this.maxValueLength = Integer.MIN_VALUE;
//			this.maxStringLength = 0;
//			int space = getTreeMaxLevel(root);
//			List<String> subList = new ArrayList<>();
//			System.out.println();
//			System.out.println("---New Tree--------------");
//			if (space < 0) {
//				System.out.println(" () ");
//				return;
//			}
//
//			while (!printList.isEmpty()) {
//				int index = levelList.poll();
//				subList = printList.subList(0, index);
//				printRow(space, subList, subList.size());
//				printList = printList.subList(index, printList.size());
//				space /= 2;
//			}
//		}
//
//		private int getTreeMaxLevel(Node<K, V> root) {
//			int maxLevel = 0;
//			int levelIndex = 0;
//			String maxValueStr = "";
//			if (root == null) {
//				maxLevel = -1;
//				return maxLevel;
//			}
//			List<String> tempList = new ArrayList<>();
//			levelList = new LinkedList<>();
//			Queue<Node> queue = new LinkedList<>();
//			queue.add(root);
//			boolean isEmptyRow = true;
//
//			while (!queue.isEmpty()) {
//				int levelSize = queue.size();
//				levelList.add(levelSize);
//				for (Node node : queue) {
//					if (node != null) {
//						isEmptyRow = false;
//						break;
//					}
//				}
//				if (!isEmptyRow) {
//					for (int i = 0; i < levelSize; i++) {
//						Node currentNode = queue.poll();
//						if (currentNode != null) {
//							tempList.add(currentNode.toString());
//							maxValueStr = currentNode.toString();
//							if (maxValueStr.length() >= maxValueLength)
//								maxValueLength = maxValueStr.length();
//							if (currentNode.left != null) {
//								queue.add(currentNode.left);
//							} else
//								queue.add(null);
//							if (currentNode.right != null) {
//								queue.add(currentNode.right);
//							} else
//								queue.add(null);
//						} else {
//							tempList.add("()");
//							queue.add(null);
//							queue.add(null);
//						}
//					}
//					levelIndex = levelSize;
//					isEmptyRow = true;
//				} else {
//					maxLevel = levelSize;
//					break;
//				}
//			}
//			//int index = tempList.size() - maxLevel / 2;
//			//tempList.add(index, "flag");
//			printList = adjustValues(maxValueLength, tempList);
//			maxStringLength = (maxValueLength + 1) * levelIndex;
//			return maxLevel;
//		}
//
//		private List<String> adjustValues(int maxValueLength, List<String> values) {
//			String gapDash = "_";
//			String gapSpace = " ";
//			int mark = values.indexOf("flag");
//			int startSpace = 0;
//			int endSpace = 0;
//			int lengthDiff = 0;
//			String value = "";
//			String newValue = "";
//
//			List<String> changeValues = new ArrayList<>();
//			for (int i = 0; i < values.size(); i++) {
//				value = values.get(i);
//				lengthDiff = maxValueLength - value.length();
//				startSpace = (int) Math.floor(((lengthDiff) * 1.0) / 2);
//				endSpace = (int) Math.ceil(((lengthDiff) * 1.0) / 2);
//
//				if (!value.equals("flag")) {
//					if (lengthDiff > 0) {
//						if (i < mark)
//							newValue = gapDash.repeat(startSpace) + value + gapDash.repeat(endSpace);
//						if (i > mark)
//							newValue = gapSpace.repeat(startSpace) + value + gapSpace.repeat(endSpace);
//						changeValues.add(newValue);
//					} else
//						changeValues.add(value);
//				}
//			}
//			return changeValues;
//		}
//
//		private void printRow(int space, List<String> list, int index) {
//			String gapSpace = " ";
//			String gapDash = "_";
//			int startValueHalf = (int) Math.floor(((maxValueLength) * 1.0) / 2);
//			int endValueHalf = (int) Math.ceil(((maxValueLength) * 1.0) / 2);
//
//			int startPlainCount = space - startValueHalf;
//			int tmpStartSpacePlainCount = (int) Math.ceil(((startPlainCount) * 1.0) / 2);
//			int tmpStartDashPlainCount = (int) Math.floor(((startPlainCount) * 1.0) / 2);
//			int startDashPlainCount = tmpStartDashPlainCount > startValueHalf ? tmpStartDashPlainCount - startValueHalf : tmpStartDashPlainCount;
//			int startSpacePlainCount = tmpStartDashPlainCount > startValueHalf ? tmpStartSpacePlainCount + startValueHalf : tmpStartSpacePlainCount;
//
//			int endPlainCount = space - endValueHalf;
//			int tmpEndDashPlainCount = (int) Math.floor(((endPlainCount) * 1.0) / 2);
//			int tmpEndSpacePlainCount = (int) Math.ceil(((endPlainCount) * 1.0) / 2);
//			int endDashPlainCount = tmpEndDashPlainCount > endValueHalf ? tmpEndDashPlainCount - endValueHalf : tmpEndDashPlainCount;
//			int endSpacePlainCount = tmpEndDashPlainCount > endValueHalf ? tmpEndSpacePlainCount + endValueHalf : tmpEndSpacePlainCount;
//
//			int startHardCount = ((maxStringLength / (index * 2) - space) + space) - startValueHalf;
//			int tmpStartSpaceHardCount = (int) Math.ceil(((startHardCount) * 1.0) / 2);
//			int tmpStartDashHardCount = (int) Math.floor(((startHardCount) * 1.0) / 2);
//			int startDashHardCount = tmpStartDashHardCount > startValueHalf ? tmpStartDashHardCount - startValueHalf : tmpStartDashHardCount;
//			int startSpaceHardCount = tmpStartDashHardCount > startValueHalf ? tmpStartSpaceHardCount + startValueHalf : tmpStartSpaceHardCount;
//
//			int endHardCount = ((maxStringLength / (index * 2) - space) + space) - endValueHalf;
//			int tmpEndDashHardCount = (int) Math.ceil(((endHardCount) * 1.0) / 2);
//			int tmpEndSpaceHardCount = (int) Math.floor(((endHardCount) * 1.0) / 2);
//			int endDashHardCount = tmpEndDashHardCount > endValueHalf ? tmpEndDashHardCount - endValueHalf : tmpEndDashHardCount;
//			int endSpaceHardCount = tmpEndDashHardCount > endValueHalf ? tmpEndSpaceHardCount + endValueHalf : tmpEndSpaceHardCount;
//
//			LinkedList<String> valueList = new LinkedList<>(list);
//			String startSpaceHalf = "";
//			String startDashHalf = "";
//			String endSpaceHalf = gapSpace;
//			String endDashHalf = "";
//
//			while (!valueList.isEmpty()) {
//				String value = valueList.poll();
//				if (maxValueLength <= 4) {
//					startSpaceHalf = gapSpace.repeat(startSpacePlainCount);
//					endSpaceHalf = gapSpace.repeat(endSpacePlainCount);
//					startDashHalf = gapDash.repeat(startDashPlainCount);
//					endDashHalf = gapDash.repeat(endDashPlainCount);
//				} else {
//					if (space >= startValueHalf) {
//						startSpaceHalf = gapSpace.repeat(startSpaceHardCount);
//						endSpaceHalf = gapSpace.repeat(endSpaceHardCount);
//						startDashHalf = gapDash.repeat(startDashHardCount);
//						if (space != 4)
//							endDashHalf = gapDash.repeat(endDashHardCount);
//						else
//							endDashHalf = gapSpace.repeat(endDashHardCount);
//					}
//				}
//				StringBuilder sb = new StringBuilder();
//				sb.append(startSpaceHalf + startDashHalf + value + endDashHalf + endSpaceHalf);
//				System.out.print(sb.toString());
//			}
//			System.out.println();
//		}
//	}
